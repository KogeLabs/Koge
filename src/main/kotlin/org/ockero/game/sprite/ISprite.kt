/**
 * Copyright (C) 2020 Moncef YABI
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

import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.BodyDef
import org.jbox2d.dynamics.FixtureDef
import org.jbox2d.dynamics.World
import org.joml.Vector2f
import org.joml.Vector3f
import org.ockero.engine.graphics.Model


/**
 * The Sprite interface
 *
 * @author Moncef YABI
 */
interface ISprite {


    var position: Vector2f
    var angleOfRotation:Float
    var scale:Vector3f
    var mainModel: Model
    var physicsBody: Body?

    /**
     * Init the Sprite and create a texture from the loaded image.
     *
     */
    fun init(world: World?)

    /**
     * Update the sprite data
     *
     */
    fun update()

    /**
     * Setup the physics body
     *
     */
    fun setupPhysicsBody(bd: BodyDef, fd: FixtureDef)

    /**
     * Apply an impulse at the center point of the sprite. This immediately modifies the velocity.
     *
     */
    fun applyLinearImpulse(impulseOnX: Float, impulseOnY:Float)

    /**
     * Apply an angular impulse.
     *
     */
    fun applyAngularImpulse(impulse: Float)

    /**
     * Move the sprite in the x direction at speedX
     *
     * @param speedX
     */
    fun moveX(speedX: Float)

    /**
     * Move the sprite in the x direction at speedY
     *
     * @param speedY
     */
    fun moveY(speedY: Float)

    /**
     * Change the Sprite 2d coordinate
     *
     * @param x
     * @param y
     */
    fun setPosition(x:Float, y:Float)

    /**
     * Destroy this sprite and free all resources.
     *
     */
    fun destroy()

    /**
     * Checks if this Sprite intersects another Sprite
     *
     * @param oderSprite
     * @return Boolean
     */
    fun collide(oderSprite: Sprite): Boolean

    /**
     *
     * @return Sprite with
     */
    fun getWith():Float

    /**
     *
     * @return Sprite height
     */
    fun getHeight():Float

    /**
     *
     * @return active model
     */
    fun getModel():Model
}