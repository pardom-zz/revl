package com.revl.challenge.interactor

import com.revl.challenge.interactor.Interactor.Request
import com.revl.challenge.interactor.Interactor.Response
import io.reactivex.Single

/**
 * An interface for abstracting logic into an executable object. Accepts a request object for
 * execution and asynchronously return a response object.
 */
interface Interactor<in I : Request, O : Response> {

    /**
     * Execute the logic encapsulated in this interactor.
     *
     * @param request The request object.
     * @return An asynchronous response.
     */
    fun execute(request: I): Single<O>

    /**
     * Interface used to enforce interactor request pattern.
     */
    interface Request

    /**
     * Interface used to enforce interactor response pattern.
     */
    interface Response

}
