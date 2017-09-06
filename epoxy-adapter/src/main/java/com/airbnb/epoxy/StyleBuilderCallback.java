package com.airbnb.epoxy;

public interface StyleBuilderCallback<T> {
  void buildStyle(T builder);
}