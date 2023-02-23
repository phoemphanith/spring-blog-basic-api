package com.phoemphanith.blogserviceapibasic.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null
)