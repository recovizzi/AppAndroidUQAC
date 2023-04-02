package com.example.lepeenice.UIDisplay

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lepeenice.MemoryClassPackage.GameManager
import com.example.lepeenice.MemoryClassPackage.Monster
import com.example.lepeenice.MemoryClassPackage.Player
import com.example.lepeenice.PlaySound
import com.example.lepeenice.ui.theme.LEpeeNiceTheme

class MainScreen {
    companion object {
        var Life = mutableStateOf(GameManager.getInstance().currentMonsterLife)
        @Composable
        fun MainScreen() {
            val currentContext: Context = LocalContext.current
            LEpeeNiceTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colors.secondary,
                                    MaterialTheme.colors.surface
                                ),
                                startY = 1000f,
                                endY = 2000f
                            )
                        )
                ) {
                    Column() {
                        var Life = GameManager.getInstance().currentMonsterLife
                        GameManager.getInstance().currentMoney = Player.getInstance().getMoney()
                        var money = GameManager.getInstance().currentMoney
                        /*
                Score display code UI
                 */
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(color = MaterialTheme.colors.background)
                                .padding(start = 20.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.CenterStart)
                            ) {
                                Prefab.CustomTitre(content = "Money : ")
                                /*
                            Texte pour le score à modifier lors du jeu
                             */
                                Prefab.CustomTitre(content = money.toString())
                                Prefab.CustomTitre(content = " CAD")
                            }
                        }

                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            /*
                        Image du monstre
                         */
                            Prefab.CustomImage(
                                source = if ((Life.toFloat() /GameManager.getInstance().currentMonster.hp.toFloat())>0.5f){GameManager.getInstance().currentMonster.imageUri}else{GameManager.getInstance().currentMonster.imageUri2},
                                contentDescription = "MobPicture",
                                size = 240.dp,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {

                            CustomComposable.LifeBar(currentLife = Life, maxLife = GameManager.getInstance().currentMonster.hp)

                            Box(modifier = Modifier.align(Alignment.Center)) {
                                Prefab.CustomTitre(content = GameManager.getInstance().currentMonster.name)
                            }
                        }
                        /*
                    Bouton pour tester la bar de vie volatile
                     */
                        Row {
                            Prefab.CustomButton(text = "Attaque", onClick = {
                                GameManager.getInstance().dealDamagestest()
                                //Life = GameManager.getInstance().currentMonsterLife
                                Player.getInstance().addMoney(1000)
                                PlaySound.playSound(currentContext, com.example.lepeenice.R.raw.sword_metal_woosh, false)
                            })
                        }

                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            /*
                        Image de l'épée
                         */
                            CustomComposable.MirroredImage(image = painterResource(Player.getInstance().sword.imageId), isMirrored = GameManager.getInstance().currentBoolAttack)
                        }
                    }


                    //Show version, dont remove this on the preview !
                    Box(modifier = Modifier.align(Alignment.BottomStart)) {
                        CustomComposable.ModeDisplay(
                            name = "App Mode",
                            version = "Version : 0.0.3"
                        )
                    }
                }
            }
        }
    }
}