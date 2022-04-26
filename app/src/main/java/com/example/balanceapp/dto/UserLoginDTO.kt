package com.example.balanceapp.dto

class UserLoginDTO constructor(password: String,email: String){
    private var password: String? = password;
    private var email: String? = email;

    fun getpassword(): String?{
        return (this.password)
    }
    fun setpassword(password: String)
    {
        this.password=password
    }
    fun  getemail(): String?{
        return (this.email)
    }
    fun setemail(email: String)
    {
        this.email=email
    }
}