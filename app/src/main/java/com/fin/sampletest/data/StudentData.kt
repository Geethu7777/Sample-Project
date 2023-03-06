package com.fin.sampletest.data

class StudentData(s: String, s1: String) {
    private val serialVersionUID = 1L

    private var name: String? = null

    private var emailId: String? = null

    fun Student() {}
    fun Student(name: String?, emailId: String?) {
        this.name = name
        this.emailId = emailId
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getEmailId(): String? {
        return emailId
    }

    fun setEmailId(emailId: String?) {
        this.emailId = emailId
    }


}