package org.example.marketinguserprofileserver.dto

import org.example.marketinguserprofileserver.table.BaseDateLongIdTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

abstract class BaseDateEntity(id: EntityID<Long>, table: BaseDateLongIdTable) : LongEntity(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}