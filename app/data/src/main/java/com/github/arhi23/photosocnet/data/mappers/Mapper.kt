package com.github.arhi23.photosocnet.data.mappers


interface Mapper<F, T> {
  suspend fun map(from: F): T
}