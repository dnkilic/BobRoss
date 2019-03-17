package com.example.bobross.base.repository

interface Repository<T> {
    fun getList(): List<T>
}