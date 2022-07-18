package com.dato.chatty.repo

import com.dato.chatty.model.Room
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface RoomRepo : MongoRepository<Room, String> {

    @Query("{\$and:[{userIds:?0},{'userIds.1':{\$exists:true}},{'userIds.2':{\$exists:false}},{isMultiChat:false}]}")
    fun findRoomWithUser(userIds: Set<String?>): Optional<Room>

}