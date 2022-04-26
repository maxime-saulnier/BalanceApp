package com.example.balanceapp.dto

class UserRegisterDTO constructor(firstName: String, lastName: String, email: String, password: String,shortname: String,gender: String,birthdate: String,taille: String,poids: String){
    private var password: String? = password
    private var email: String? = email
    private var firstName: String? = firstName
    private var lastName: String? = lastName
    private var shortname: String? = shortname
    private var gender: String? = gender
    private var birthdate: String? = birthdate
    private var taille: String? = taille
    private var poids: String? = poids

    fun getPassword(): String?{
        return (this.password)
    }
    fun setPassword(password: String)
    {
        this.password=password
    }
    fun getEmail(): String?{
        return (this.email)
    }
    fun setEmail(email: String)
    {
        this.email=email
    }
    fun getFirstName(): String?{
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

    fun getShortname(): String?{
        return (this.shortname)
    }
    fun setShortname(shortname: String)
    {
        this.shortname=shortname
    }
    fun getGender(): String?{
        return (this.gender)
    }
    fun setGender(gender: String)
    {
        this.gender=gender
    }
    fun getBirthdate(): String?{
        return (this.birthdate)
    }
    fun setBirthdate(birthdate: String)
    {
        this.birthdate=birthdate
    }
    fun getTaille(): String?
    {
        return (this.taille)
    }
    fun setTaille(taille: String)
    {
        this.taille=taille
    }
    fun getPoids(): String?
    {
        return (this.poids)
    }
    fun setPoids(poids: String)
    {
        this.poids=poids
    }
}