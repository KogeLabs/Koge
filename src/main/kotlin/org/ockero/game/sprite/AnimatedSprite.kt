/*
 * Copyright (C) 2014 Moncef YABI
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.ockero.game.sprite

import org.jbox2d.dynamics.World
import org.joml.Vector2f
import org.ockero.engine.exception.TextureNotSetException
import org.ockero.engine.graphics.Model
import org.ockero.engine.graphics.texture.Texture
import org.ockero.engine.graphics.texture.TextureLoader
import org.ockero.game.animation.Animation
import org.ockero.game.animation.Frame



/**
 * This is the animated Sprite class
 *
 * @author Moncef YABI
 */
class AnimatedSprite(private val rows:Int, private val columns:Int) : Sprite() {
    private var models= arrayOf<Model>()
    private val animationsFrames: ArrayList<Animation> = ArrayList()
    private val frames: ArrayList<Frame>  =  ArrayList()

    var delay = 0

    lateinit var activeAnimation: Animation
    lateinit var sheetTexture: Texture
    var activeAnimationName: String? = null

    /**
     * Init the animation object
     *
     */
    override fun init(world: World?) {

        if (texturePath == "") throw TextureNotSetException("Texture path was not set!!")
        this.world=world
        createPhysicsBody(world)
        position= Vector2f(xPos, yPos)
        sheetTexture = TextureLoader.create(texturePath)
        models = SpriteSheetUtils.getModelsFromSpriteSheet(sheetTexture, rows, columns)
        var tempImageArray= arrayOf<Model>()
        for (frame in frames) {
            if(frame.name=="jump_right")
            {
                var x=0
                x=1
            }
            val count = frame.end-frame.start

            if(count<0)
                continue

            if (count >= 1) {
                tempImageArray = models.copyOfRange(frame.start-1, frame.end)

            } else if (count == 0) {
                tempImageArray = arrayOf()
                tempImageArray += models[frame.start + count - 1]

            }

            animationsFrames.add(Animation(frame.name, tempImageArray, delay, frame.loop))
        }
        setActiveAnimation(activeAnimationName)
    }

    /**
     * Start the animation
     *
     */
    fun startAnimation()
    {
        activeAnimation.start=true
        activeAnimation.stoped=false
    }

    /**
     * Stop the animation
     *
     */
    fun stopAnimation()
    {
        activeAnimation.start=false
        activeAnimation.stoped=true
    }


    /**
     * Select an active animation
     *
     * @param index
     */
    fun setActiveAnimation(index: Int) {
        activeAnimation = animationsFrames[index]
    }

    /**
     * Select an active animation
     *
     * @param name
     */
    fun setActiveAnimation(name: String?) {
        for (animation in animationsFrames) {
            if (animation.name == name) {
                activeAnimation = animation
            }
        }
        mainModel=getModel()
    }

    /**
     * Update the animation and return the updated model
     *
     * @return
     */
    override fun getModel(): Model {

        activeAnimation.update()
        return activeAnimation.models[activeAnimation.counter]
    }

    /**
     * Destroy the animations and the main texture
     *
     */
    override fun destroy() {
        animationsFrames.forEach { animation ->
            animation.models.forEach {model ->
                model.destroy()
            }
        }

        sheetTexture.destroy()
    }

    /**
     * DSL Wrapper for the frame function
     *
     * @param block: lambda function
     */
    fun frame(block: Frame.()->Unit)  {
        frames.add(Frame().apply(block))
    }
}

/**
 * DSL Wrapper for the fsprite function
 *
 * @param block: lambda function
 */
fun animatedsprite(rows:Int, columns:Int, block: AnimatedSprite.()->Unit): AnimatedSprite = AnimatedSprite(rows,columns).apply(block)