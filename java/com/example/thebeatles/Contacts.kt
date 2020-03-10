package com.example.thebeatles

class Contacts
{
    private var name : String = ""
    private var title : String = ""
    private var pic : String = ""

    constructor(name : String, title : String, pic : String)
    {
        this.name = name
        this.title = title
        this.pic = pic
    }

    public fun setName(name : String)
    {
        this.name = name
    }

    public fun getName() : String
    {
        return name
    }

    public fun getTitle() : String
    {
        return title
    }

    public fun setTitle(title : String)
    {
        this.title = title
    }

    public fun setPic(pic : String)
    {
        this.pic = pic
    }

    public fun getPic() : String
    {
        return pic
    }

}