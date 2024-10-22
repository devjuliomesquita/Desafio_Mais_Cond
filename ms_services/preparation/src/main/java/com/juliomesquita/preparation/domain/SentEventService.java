package com.juliomesquita.preparation.domain;

public interface SentEventService<T> {
    void sentEvent(T data);
}
