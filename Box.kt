/*
 * Copyright (C) 2017 - present Instructure, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
@file:Suppress("unused")

package com.instructure.androidboxers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlin.reflect.KProperty

/**
 * Box
 */
abstract class Box(var boxer: Boxer = MapBoxer()) {

    private val delegates = arrayListOf<Pair<BoxProperty<Any?>, KProperty<Any?>>>()

    internal fun registerProperty(boxProp: BoxProperty<Any?>, prop: KProperty<Any?>) {
        delegates += boxProp to prop
    }

    fun transferFrom(other: Boxer) {
        val original = boxer
        boxer = other
        val values = delegates.map { (boxProp, prop) -> boxProp.getValue(this, prop) }
        boxer = original
        delegates.forEachIndexed { index, (boxProp, prop) -> boxProp.setValue(this, prop, values[index]) }
    }

    fun transferTo(other: Boxer) {
        val values = delegates.map { (boxProp, prop) -> boxProp.getValue(this, prop) }
        val original = boxer
        boxer = other
        delegates.forEachIndexed { index, (boxProp, prop) -> boxProp.setValue(this, prop, values[index]) }
        boxer = original
    }

    fun transferFrom(box: Box) = transferFrom(box.boxer)
    fun transferTo(box: Box) = transferTo(box.boxer)

    fun transferFrom(activity: Activity) = transferFrom(activity.boxer)
    fun transferTo(activity: Activity) = transferTo(activity.boxer)

    fun transferFrom(fragment: Fragment) = transferFrom(fragment.boxer)
    fun transferTo(fragment: Fragment) = transferTo(fragment.boxer)

    fun transferFrom(bundle: Bundle) = transferFrom(BundleBoxer(bundle))
    fun transferTo(bundle: Bundle) = transferTo(BundleBoxer(bundle))

    fun transferFrom(intent: Intent) = transferFrom(BundleBoxer(intent.extras ?: Bundle()))
    fun transferTo(intent: Intent) {
        intent.putExtras(BundleBoxer().apply { transferTo(this) }.bundle)
    }

    fun swapBoxers(newBoxer: Boxer) {
        val values = delegates.map { (boxProp, prop) -> boxProp.getValue(this, prop) }
        boxer = newBoxer
        delegates.forEachIndexed { index, (boxProp, prop) -> boxProp.setValue(this, prop, values[index]) }
    }

    fun asBundle(): Bundle = (boxer as? BundleBoxer)?.bundle ?: Bundle().apply { transferTo(this) }
}

fun <F : Fragment, B : Box> F.applyBox(box: B, block: B.() -> Unit = {}): F {
    box.swapBoxers(boxer)
    box.block()
    return this
}

fun <I : Intent, B : Box> I.applyBox(box: B): I {
    box.transferTo(this)
    return this
}

fun <B : Box> B.wrap(newBoxer: Boxer): B { boxer = newBoxer; return this }
fun <B : Box> B.wrap(fragment: Fragment) = wrap(fragment.boxer)
fun <B : Box> B.wrap(bundle: Bundle) = wrap(BundleBoxer(bundle))
fun <B : Box> B.wrap(activity: Activity) = wrap(activity.boxer)