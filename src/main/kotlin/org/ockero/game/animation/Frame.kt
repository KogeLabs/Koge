
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
package org.ockero.game.animation

import org.ockero.engine.kernel.GameDSLMarker

/**
 * Data class for animation frames
 *
 * @property name
 * @property count
 * @property loop
 *
 * @author Moncef YABI
 */
@GameDSLMarker
data class Frame(var name:String="", var start:Int=0, var end:Int=0, var loop:Boolean=false)