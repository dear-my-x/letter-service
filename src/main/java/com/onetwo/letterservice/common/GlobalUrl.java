package com.onetwo.letterservice.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalUrl {

    public static final String ROOT_URI = "/";
    public static final String UNDER_ROUTE = "/*";
    public static final String EVERY_UNDER_ROUTE = "/**";

    public static final String LETTER_ROOT = "/letters";

    public static final String PATH_VARIABLE_LETTER_ID = "letter-id";
    public static final String PATH_VARIABLE_LETTER_ID_WITH_BRACE = "/{" + PATH_VARIABLE_LETTER_ID + "}";
}
