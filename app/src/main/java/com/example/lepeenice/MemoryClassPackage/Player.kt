package com.example.lepeenice.MemoryClassPackage

import kotlinx.serialization.Serializable

@Serializable
public class Player private constructor() {

    var pseudo: String = "PlayerName"
    var sword: Sword = Sword("Épée d'entrainement", 1)
    var hp: Int = 100
    private var score: Int = 0

    companion object {
        private var instance: Player? = null

        fun getInstance(): Player {
            if (instance == null) {
                instance = Player()
            }
            return instance as Player
        }
    }

    fun addScore(pts: Int) {
        score += pts;
    }

    fun getScore(): Int {
        return score
    }
}