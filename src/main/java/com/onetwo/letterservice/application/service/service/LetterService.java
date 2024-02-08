package com.onetwo.letterservice.application.service.service;

import com.onetwo.letterservice.application.port.in.command.DeleteLetterCommand;
import com.onetwo.letterservice.application.port.in.command.RegisterLetterCommand;
import com.onetwo.letterservice.application.port.in.response.DeleteLetterResponseDto;
import com.onetwo.letterservice.application.port.in.response.RegisterLetterResponseDto;
import com.onetwo.letterservice.application.port.in.usecase.DeleteLetterUseCase;
import com.onetwo.letterservice.application.port.in.usecase.RegisterLetterUseCase;
import com.onetwo.letterservice.application.port.out.ReadLetterPort;
import com.onetwo.letterservice.application.port.out.RegisterLetterPort;
import com.onetwo.letterservice.application.port.out.UpdateLetterPort;
import com.onetwo.letterservice.application.service.converter.LetterUseCaseConverter;
import com.onetwo.letterservice.common.exceptions.BadRequestException;
import com.onetwo.letterservice.common.exceptions.NotFoundResourceException;
import com.onetwo.letterservice.common.exceptions.ResourceAlreadyDeletedException;
import com.onetwo.letterservice.domain.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LetterService implements RegisterLetterUseCase, DeleteLetterUseCase {

    private final RegisterLetterPort registerLetterPort;
    private final ReadLetterPort readLetterPort;
    private final UpdateLetterPort updateLetterPort;
    private final LetterUseCaseConverter letterUseCaseConverter;

    /**
     * Register letter use case,
     * register letter data to persistence
     *
     * @param registerLetterCommand data about register letter with user id and receiver user id
     * @return Boolean about register success
     */
    @Override
    @Transactional
    public RegisterLetterResponseDto registerLetter(RegisterLetterCommand registerLetterCommand) {
        Letter newLetter = Letter.createNewLetterByCommand(registerLetterCommand);

        Letter savedLetter = registerLetterPort.registerLetter(newLetter);

        return letterUseCaseConverter.letterToRegisterResponseDto(savedLetter);
    }

    /**
     * Delete letter use case,
     * delete letter data to persistence
     *
     * @param deleteLetterCommand request delete data about letter's userid, letter's id
     * @return Boolean about delete letter success
     */
    @Override
    @Transactional
    public DeleteLetterResponseDto deleteLetter(DeleteLetterCommand deleteLetterCommand) {
        Letter letter = checkLetterExistAndGetLetter(deleteLetterCommand.getLetterId());

        boolean isSender = letter.isSender(deleteLetterCommand.getUserId());
        boolean isReceiver = letter.isReceiver(deleteLetterCommand.getUserId());

        if (!isSender && !isReceiver) throw new BadRequestException("request user is not letter's sender or receiver");

        if (isSender) letterSenderDelete(letter);
        else letterReceiverDelete(letter);

        updateLetterPort.updateLetter(letter);

        boolean result = (isSender && letter.isDeletedFromSender()) || (isReceiver && letter.isDeletedFromReceiver());

        return letterUseCaseConverter.letterToDeleteResponseDto(result);
    }

    private Letter checkLetterExistAndGetLetter(Long letterId) {
        Optional<Letter> optionalLetter = readLetterPort.findById(letterId);

        if (optionalLetter.isEmpty()) throw new NotFoundResourceException("Letter dose not exist");

        return optionalLetter.get();
    }

    private void letterSenderDelete(Letter letter) {
        if (letter.isDeletedFromSender())
            throw new ResourceAlreadyDeletedException("letter already deleted from sender");

        letter.senderDelete();
    }

    private void letterReceiverDelete(Letter letter) {
        if (letter.isDeletedFromReceiver())
            throw new ResourceAlreadyDeletedException("letter already deleted from receiver");

        letter.receiverDelete();
    }
}
