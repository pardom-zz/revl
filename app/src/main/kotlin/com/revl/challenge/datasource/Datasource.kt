package com.revl.challenge.datasource

import io.reactivex.Single

/**
 * Abstraction for concrete data sources.
 */
interface Datasource<in K : Any, V> {

    fun get(key: K): Single<V>

    fun put(key: K, value: V): Single<V>

    fun remove(key: K): Single<V>

}
