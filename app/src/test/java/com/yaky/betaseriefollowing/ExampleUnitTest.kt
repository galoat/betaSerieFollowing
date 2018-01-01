package com.yaky.betaseriefollowing

import com.yaky.betaseriefollowing.data.classes.User
import org.junit.Assert.assertEquals
import org.junit.Test


class UserTest {
    @Test
    fun test_MD5() {
        var user = User()
        val password = "aaaaa"
        val md5 = "594f803b380a41396ed63dca39503542"
        assertEquals("fonction md5 notcorrectly implementd", md5,  User.convertPassMd5(password))
        user.password = password
        assertEquals("using geter the password is not set", user.password, md5)
        user= User("test",password)
        assertEquals("using construcor the password is not set",user.password, md5)
    }
}
