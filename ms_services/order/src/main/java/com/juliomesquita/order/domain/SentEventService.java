package com.juliomesquita.order.domain;

public interface SentEventService<T> {
    void sentEvent(T data);
}
