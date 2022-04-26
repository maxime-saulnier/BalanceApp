package com.example.balanceapp.dto

class UserDTO constructor(firstName: String, lastName: String, email: String){
    private var firstName: String? = firstName;
    private var lastName: String? = lastName;
    private var email: String? = email;

    fun  getFirstName(): String?{
        return (this.firstName)
    }
    fun setFirstName(firstName: String)
    {
        this.firstName=firstName
    }
    fun getLastName(): String?
    {
        return (this.lastName)
    }
    fun setLastName(lastName: String)
    {
        this.lastName=lastName
    }
    fun getEmail(): String?
    {
        return (this.email)
    }
    fun setEmail(email: String)
    {
        this.email=email
    }
}