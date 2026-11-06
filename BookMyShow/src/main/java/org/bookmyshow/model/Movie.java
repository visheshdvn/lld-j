package org.bookmyshow.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Movie {
    private final String id;
    private final String title;
    private final int durationInMins;
}
