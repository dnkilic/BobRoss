package com.example.bobross.repository.model

const val ERROR_POST_ARE_NOT_AVAILABLE = -100
const val ERROR_NETWORK = -101
const val ERROR_UNKNOWN = -104

class ExampleException(code: Int) : Exception()