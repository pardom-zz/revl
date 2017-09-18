package com.revl.challenge.foo

sealed class FooAction {
    data class GetFoo(val id: String) : FooAction()
}
