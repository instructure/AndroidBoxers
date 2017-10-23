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

import android.os.IBinder
import android.os.Parcelable
import android.util.SparseArray
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


@Suppress("UNCHECKED_CAST")
abstract class BoxProperty<T> : ReadWriteProperty<Box, T> {

    private var keyName = ""

    abstract fun Boxer.get(key: String): T
    abstract fun Boxer.set(key: String, value: T)

    operator fun provideDelegate(thisRef: Box, prop: KProperty<*>): ReadWriteProperty<Box, T> {
        thisRef.registerProperty(this as BoxProperty<Any?>, prop)
        keyName = "${thisRef::class.java.canonicalName}$${prop.name}"
        return this
    }

    override fun getValue(thisRef: Box, property: KProperty<*>): T = thisRef.boxer.get(keyName)
    override fun setValue(thisRef: Box, property: KProperty<*>, value: T) = thisRef.boxer.set(keyName, value)

    fun withKey(newKey: String) = this.apply { keyName = newKey }
}

class BoxBinder(val default: IBinder) : BoxProperty<IBinder>() {
    override fun Boxer.get(key: String): IBinder = getBinder(key) ?: default
    override fun Boxer.set(key: String, value: IBinder) = putBinder(key, value)
}

class NBoxBinder(val default: IBinder? = null) : BoxProperty<IBinder?>() {
    override fun Boxer.get(key: String): IBinder? = getBinder(key) ?: default
    override fun Boxer.set(key: String, value: IBinder?) = putBinder(key, value)
}

class BooleanBinder(val default: Boolean = false) : BoxProperty<Boolean>() {
    override fun Boxer.get(key: String) = getBoolean(key) ?: default
    override fun Boxer.set(key: String, value: Boolean) = putBoolean(key, value)
}

class NBooleanBinder(val default: Boolean? = null) : BoxProperty<Boolean?>() {
    override fun Boxer.get(key: String) = getBoolean(key) ?: default
    override fun Boxer.set(key: String, value: Boolean?) = putBoolean(key, value)
}

class BoxBoolean(val default: Boolean = false) : BoxProperty<Boolean>() {
    override fun Boxer.get(key: String) = getBoolean(key) ?: default
    override fun Boxer.set(key: String, value: Boolean) = putBoolean(key, value)
}

class NBoxBoolean(val default: Boolean? = null) : BoxProperty<Boolean?>() {
    override fun Boxer.get(key: String) = getBoolean(key) ?: default
    override fun Boxer.set(key: String, value: Boolean?) = putBoolean(key, value)
}

class BoxBooleanArray(val default: BooleanArray = booleanArrayOf()) : BoxProperty<BooleanArray>() {
    override fun Boxer.get(key: String) = getBooleanArray(key) ?: default
    override fun Boxer.set(key: String, value: BooleanArray) = putBooleanArray(key, value)
}

class NBoxBooleanArray(val default: BooleanArray? = null) : BoxProperty<BooleanArray?>() {
    override fun Boxer.get(key: String) = getBooleanArray(key) ?: default
    override fun Boxer.set(key: String, value: BooleanArray?) = putBooleanArray(key, value)
}

class BoxByte(val default: Byte = 0.toByte()) : BoxProperty<Byte>() {
    override fun Boxer.get(key: String) = getByte(key) ?: default
    override fun Boxer.set(key: String, value: Byte) = putByte(key, value)
}

class NBoxByte(val default: Byte? = null) : BoxProperty<Byte?>() {
    override fun Boxer.get(key: String) = getByte(key) ?: default
    override fun Boxer.set(key: String, value: Byte?) = putByte(key, value)
}

class BoxByteArray(val default: ByteArray = byteArrayOf()) : BoxProperty<ByteArray>() {
    override fun Boxer.get(key: String) = getByteArray(key) ?: default
    override fun Boxer.set(key: String, value: ByteArray) = putByteArray(key, value)
}

class NBoxByteArray(val default: ByteArray? = null) : BoxProperty<ByteArray?>() {
    override fun Boxer.get(key: String) = getByteArray(key) ?: default
    override fun Boxer.set(key: String, value: ByteArray?) = putByteArray(key, value)
}

class BoxChar(val default: Char = 0.toChar()) : BoxProperty<Char>() {
    override fun Boxer.get(key: String) = getChar(key) ?: default
    override fun Boxer.set(key: String, value: Char) = putChar(key, value)
}

class NBoxChar(val default: Char? = null) : BoxProperty<Char?>() {
    override fun Boxer.get(key: String) = getChar(key) ?: default
    override fun Boxer.set(key: String, value: Char?) = putChar(key, value)
}

class BoxCharArray(val default: CharArray = charArrayOf()) : BoxProperty<CharArray>() {
    override fun Boxer.get(key: String) = getCharArray(key) ?: default
    override fun Boxer.set(key: String, value: CharArray) = putCharArray(key, value)
}

class NBoxCharArray(val default: CharArray? = null) : BoxProperty<CharArray?>() {
    override fun Boxer.get(key: String) = getCharArray(key) ?: default
    override fun Boxer.set(key: String, value: CharArray?) = putCharArray(key, value)
}

class BoxCharSequence(val default: CharSequence = "") : BoxProperty<CharSequence>() {
    override fun Boxer.get(key: String) = getCharSequence(key) ?: default
    override fun Boxer.set(key: String, value: CharSequence) = putCharSequence(key, value)
}

class NBoxCharSequence(val default: CharSequence? = null) : BoxProperty<CharSequence?>() {
    override fun Boxer.get(key: String) = getCharSequence(key) ?: default
    override fun Boxer.set(key: String, value: CharSequence?) = putCharSequence(key, value)
}

class BoxCharSequenceArray(val default: Array<CharSequence> = arrayOf()) : BoxProperty<Array<CharSequence>>() {
    override fun Boxer.get(key: String) = getCharSequenceArray(key) ?: default
    override fun Boxer.set(key: String, value: Array<CharSequence>) = putCharSequenceArray(key, value)
}

class NBoxCharSequenceArray(val default: Array<CharSequence>? = null) : BoxProperty<Array<CharSequence>?>() {
    override fun Boxer.get(key: String) = getCharSequenceArray(key) ?: default
    override fun Boxer.set(key: String, value: Array<CharSequence>?) = putCharSequenceArray(key, value)
}

class BoxCharSequenceArrayList(val default: ArrayList<CharSequence> = arrayListOf()) : BoxProperty<ArrayList<CharSequence>>() {
    override fun Boxer.get(key: String) = getCharSequenceArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<CharSequence>) = putCharSequenceArrayList(key, value)
}

class NBoxCharSequenceArrayList(val default: ArrayList<CharSequence>? = null) : BoxProperty<ArrayList<CharSequence>?>() {
    override fun Boxer.get(key: String) = getCharSequenceArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<CharSequence>?) = putCharSequenceArrayList(key, value)
}

class BoxDouble(val default: Double = 0.0) : BoxProperty<Double>() {
    override fun Boxer.get(key: String) = getDouble(key) ?: default
    override fun Boxer.set(key: String, value: Double) = putDouble(key, value)
}

class NBoxDouble(val default: Double? = null) : BoxProperty<Double?>() {
    override fun Boxer.get(key: String) = getDouble(key) ?: default
    override fun Boxer.set(key: String, value: Double?) = putDouble(key, value)
}

class BoxDoubleArray(val default: DoubleArray = doubleArrayOf()) : BoxProperty<DoubleArray>() {
    override fun Boxer.get(key: String) = getDoubleArray(key) ?: default
    override fun Boxer.set(key: String, value: DoubleArray) = putDoubleArray(key, value)
}

class NBoxDoubleArray(val default: DoubleArray? = null) : BoxProperty<DoubleArray?>() {
    override fun Boxer.get(key: String) = getDoubleArray(key) ?: default
    override fun Boxer.set(key: String, value: DoubleArray?) = putDoubleArray(key, value)
}

class BoxFloat(val default: Float = 0.0f) : BoxProperty<Float>() {
    override fun Boxer.get(key: String) = getFloat(key) ?: default
    override fun Boxer.set(key: String, value: Float) = putFloat(key, value)
}

class NBoxFloat(val default: Float? = null) : BoxProperty<Float?>() {
    override fun Boxer.get(key: String) = getFloat(key) ?: default
    override fun Boxer.set(key: String, value: Float?) = putFloat(key, value)
}

class BoxFloatArray(val default: FloatArray = floatArrayOf()) : BoxProperty<FloatArray>() {
    override fun Boxer.get(key: String) = getFloatArray(key) ?: default
    override fun Boxer.set(key: String, value: FloatArray) = putFloatArray(key, value)
}

class NBoxFloatArray(val default: FloatArray? = null) : BoxProperty<FloatArray?>() {
    override fun Boxer.get(key: String) = getFloatArray(key) ?: default
    override fun Boxer.set(key: String, value: FloatArray?) = putFloatArray(key, value)
}

class BoxInt(val default: Int = 0) : BoxProperty<Int>() {
    override fun Boxer.get(key: String) = getInt(key) ?: default
    override fun Boxer.set(key: String, value: Int) = putInt(key, value)
}

class NBoxInt(val default: Int? = null) : BoxProperty<Int?>() {
    override fun Boxer.get(key: String) = getInt(key) ?: default
    override fun Boxer.set(key: String, value: Int?) = putInt(key, value)
}

class BoxIntArray(val default: IntArray = intArrayOf()) : BoxProperty<IntArray>() {
    override fun Boxer.get(key: String) = getIntArray(key) ?: default
    override fun Boxer.set(key: String, value: IntArray) = putIntArray(key, value)
}

class NBoxIntArray(val default: IntArray? = null) : BoxProperty<IntArray?>() {
    override fun Boxer.get(key: String) = getIntArray(key) ?: default
    override fun Boxer.set(key: String, value: IntArray?) = putIntArray(key, value)
}

class BoxIntArrayList(val default: ArrayList<Int> = arrayListOf()) : BoxProperty<ArrayList<Int>>() {
    override fun Boxer.get(key: String) = getIntArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<Int>) = putIntArrayList(key, value)
}

class NBoxIntArrayList(val default: ArrayList<Int>? = null) : BoxProperty<ArrayList<Int>?>() {
    override fun Boxer.get(key: String) = getIntArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<Int>?) = putIntArrayList(key, value)
}

class BoxLong(val default: Long = 0L) : BoxProperty<Long>() {
    override fun Boxer.get(key: String) = getLong(key) ?: default
    override fun Boxer.set(key: String, value: Long) = putLong(key, value)
}

class NBoxLong(val default: Long? = null) : BoxProperty<Long?>() {
    override fun Boxer.get(key: String) = getLong(key) ?: default
    override fun Boxer.set(key: String, value: Long?) = putLong(key, value)
}

class BoxLongArray(val default: LongArray = longArrayOf()) : BoxProperty<LongArray>() {
    override fun Boxer.get(key: String) = getLongArray(key) ?: default
    override fun Boxer.set(key: String, value: LongArray) = putLongArray(key, value)
}

class NBoxLongArray(val default: LongArray? = null) : BoxProperty<LongArray?>() {
    override fun Boxer.get(key: String) = getLongArray(key) ?: default
    override fun Boxer.set(key: String, value: LongArray?) = putLongArray(key, value)
}

class BoxParcelable<P : Parcelable>(val default: P) : BoxProperty<P>() {
    override fun Boxer.get(key: String): P = getParcelable(key) ?: default
    override fun Boxer.set(key: String, value: P) = putParcelable(key, value)
}

class NBoxParcelable<P : Parcelable>(val default: P? = null) : BoxProperty<P?>() {
    override fun Boxer.get(key: String): P? = getParcelable(key) ?: default
    override fun Boxer.set(key: String, value: P?) = putParcelable(key, value)
}

class BoxParcelableArray(val default: Array<Parcelable> = emptyArray()) : BoxProperty<Array<Parcelable>>() {
    override fun Boxer.get(key: String) = getParcelableArray(key) ?: default
    override fun Boxer.set(key: String, value: Array<Parcelable>) = putParcelableArray(key, value)
}

class NBoxParcelableArray(val default: Array<Parcelable>? = null) : BoxProperty<Array<Parcelable>?>() {
    override fun Boxer.get(key: String) = getParcelableArray(key) ?: default
    override fun Boxer.set(key: String, value: Array<Parcelable>?) = putParcelableArray(key, value)
}

class BoxParcelableList<P : Parcelable>(val default: List<P> = emptyList()) : BoxProperty<List<P>>() {
    override fun Boxer.get(key: String): List<P> = getParcelableArrayList(key) ?: default
    override fun Boxer.set(key: String, value: List<P>) = putParcelableArrayList(key, ArrayList(value))
}

class NBoxParcelableList<P : Parcelable>(val default: List<P>? = null) : BoxProperty<List<P>?>() {
    override fun Boxer.get(key: String): List<P>? = getParcelableArrayList(key) ?: default
    override fun Boxer.set(key: String, value: List<P>?) = putParcelableArrayList(key, ArrayList(value))
}

class BoxParcelableArrayList<P : Parcelable>(val default: ArrayList<P> = arrayListOf()) : BoxProperty<ArrayList<P>>() {
    override fun Boxer.get(key: String): ArrayList<P> = getParcelableArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<P>) = putParcelableArrayList(key, value)
}

class NBoxParcelableArrayList<P : Parcelable>(val default: ArrayList<P>? = null) : BoxProperty<ArrayList<P>?>() {
    override fun Boxer.get(key: String): ArrayList<P>? = getParcelableArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<P>?) = putParcelableArrayList(key, value)
}

class BoxShort(val default: Short = 0) : BoxProperty<Short>() {
    override fun Boxer.get(key: String) = getShort(key) ?: default
    override fun Boxer.set(key: String, value: Short) = putShort(key, value)
}

class NBoxShort(val default: Short? = null) : BoxProperty<Short?>() {
    override fun Boxer.get(key: String) = getShort(key) ?: default
    override fun Boxer.set(key: String, value: Short?) = putShort(key, value)
}

class BoxShortArray(val default: ShortArray = shortArrayOf()) : BoxProperty<ShortArray>() {
    override fun Boxer.get(key: String) = getShortArray(key) ?: default
    override fun Boxer.set(key: String, value: ShortArray) = putShortArray(key, value)
}

class NBoxShortArray(val default: ShortArray? = null) : BoxProperty<ShortArray?>() {
    override fun Boxer.get(key: String) = getShortArray(key) ?: default
    override fun Boxer.set(key: String, value: ShortArray?) = putShortArray(key, value)
}

class BoxSparseParcelableArray(val default: SparseArray<Parcelable> = SparseArray()) : BoxProperty<SparseArray<Parcelable>>() {
    override fun Boxer.get(key: String) = getSparseParcelableArray(key) ?: default
    override fun Boxer.set(key: String, value: SparseArray<Parcelable>) = putSparseParcelableArray(key, value)
}

class NBoxSparseParcelableArray(val default: SparseArray<Parcelable>? = null) : BoxProperty<SparseArray<Parcelable>?>() {
    override fun Boxer.get(key: String) = getSparseParcelableArray(key) ?: default
    override fun Boxer.set(key: String, value: SparseArray<Parcelable>?) = putSparseParcelableArray(key, value)
}

@Suppress("UNCHECKED_CAST")
class BoxSerializable<S : Serializable>(val default: S) : BoxProperty<S>() {
    override fun Boxer.get(key: String): S = getSerializable<S>(key) ?: default
    override fun Boxer.set(key: String, value: S) = putSerializable(key, value)
}

@Suppress("UNCHECKED_CASS")
class NBoxSerializable<S : Serializable>(val default: S? = null) : BoxProperty<S?>() {
    override fun Boxer.get(key: String): S? = getSerializable<S>(key) ?: default
    override fun Boxer.set(key: String, value: S?) = putSerializable(key, value)
}

@Suppress("UNCHECKED_CAST")
class BoxSerializableList<S : Serializable>(val default: List<S> = emptyList()) : BoxProperty<List<S>>() {
    override fun Boxer.get(key: String): List<S> = getSerializable<ArrayList<S>>(key) as? List<S> ?: default
    override fun Boxer.set(key: String, value: List<S>) = putSerializable(key, ArrayList(value))
}

@Suppress("UNCHECKED_CAST")
class NBoxSerializableList<S : Serializable>(val default: List<S>? = null) : BoxProperty<List<S>?>() {
    override fun Boxer.get(key: String): List<S>? = getSerializable<ArrayList<S>>(key) as? List<S> ?: default
    override fun Boxer.set(key: String, value: List<S>?) = putSerializable(key, ArrayList(value))
}

@Suppress("UNCHECKED_CAST")
class BoxSerializableArrayList<S : Serializable>(val default: ArrayList<S> = arrayListOf()) : BoxProperty<ArrayList<S>>() {
    override fun Boxer.get(key: String): ArrayList<S> = getSerializable<ArrayList<S>>(key) as? ArrayList<S> ?: default
    override fun Boxer.set(key: String, value: ArrayList<S>) = putSerializable(key, ArrayList(value))
}

@Suppress("UNCHECKED_CAST")
class NBoxSerializableArrayList<S : Serializable>(val default: ArrayList<S>? = null) : BoxProperty<ArrayList<S>?>() {
    override fun Boxer.get(key: String): ArrayList<S>? = getSerializable<ArrayList<S>>(key) as? ArrayList<S> ?: default
    override fun Boxer.set(key: String, value: ArrayList<S>?) = putSerializable(key, ArrayList(value))
}

class BoxString(val default: String = "") : BoxProperty<String>() {
    override fun Boxer.get(key: String) = getString(key) ?: default
    override fun Boxer.set(key: String, value: String) = putString(key, value)
}

class NBoxString(val default: String? = null) : BoxProperty<String?>() {
    override fun Boxer.get(key: String) = getString(key) ?: default
    override fun Boxer.set(key: String, value: String?) = putString(key, value)
}

class BoxStringArray(val default: Array<String> = emptyArray()) : BoxProperty<Array<String>>() {
    override fun Boxer.get(key: String) = getStringArray(key) ?: default
    override fun Boxer.set(key: String, value: Array<String>) = putStringArray(key, value)
}

class NBoxStringArray(val default: Array<String>? = null) : BoxProperty<Array<String>?>() {
    override fun Boxer.get(key: String) = getStringArray(key) ?: default
    override fun Boxer.set(key: String, value: Array<String>?) = putStringArray(key, value)
}

class BoxStringArrayList(val default: ArrayList<String> = arrayListOf()) : BoxProperty<ArrayList<String>>() {
    override fun Boxer.get(key: String): ArrayList<String> = getStringArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<String>) = putStringArrayList(key, value)
}

class NBoxStringArrayList(val default: ArrayList<String>? = null) : BoxProperty<ArrayList<String>?>() {
    override fun Boxer.get(key: String): ArrayList<String>? = getStringArrayList(key) ?: default
    override fun Boxer.set(key: String, value: ArrayList<String>?) = putStringArrayList(key, value)
}
