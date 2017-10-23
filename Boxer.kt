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
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.util.SparseArray
import java.io.Serializable


/**
 * Boxer interface
 */
interface Boxer {

    val size: Int
    val isEmpty: Boolean
    val keySet: Set<String>

    fun clear()
    fun containsKey(key: String): Boolean
    fun remove(key: String)

    fun getBinder(key: String): IBinder?
    fun putBinder(key: String, value: IBinder?)

    fun getBoolean(key: String): Boolean?
    fun putBoolean(key: String, value: Boolean?)

    fun getBooleanArray(key: String): BooleanArray?
    fun putBooleanArray(key: String, value: BooleanArray?)

    fun getByte(key: String): Byte?
    fun putByte(key: String, value: Byte?)

    fun getByteArray(key: String): ByteArray?
    fun putByteArray(key: String, value: ByteArray?)

    fun getChar(key: String): Char?
    fun putChar(key: String, value: Char?)

    fun getCharArray(key: String): CharArray?
    fun putCharArray(key: String, value: CharArray?)

    fun getCharSequence(key: String): CharSequence?
    fun putCharSequence(key: String, value: CharSequence?)

    fun getCharSequenceArray(key: String): Array<CharSequence>?
    fun putCharSequenceArray(key: String, value: Array<CharSequence>?)

    fun getCharSequenceArrayList(key: String): ArrayList<CharSequence>?
    fun putCharSequenceArrayList(key: String, value: ArrayList<CharSequence>?)

    fun getDouble(key: String): Double?
    fun putDouble(key: String, value: Double?)

    fun getDoubleArray(key: String): DoubleArray?
    fun putDoubleArray(key: String, value: DoubleArray?)

    fun getFloat(key: String): Float?
    fun putFloat(key: String, value: Float?)

    fun getFloatArray(key: String): FloatArray?
    fun putFloatArray(key: String, value: FloatArray?)

    fun getInt(key: String): Int?
    fun putInt(key: String, value: Int?)

    fun getIntArray(key: String): IntArray?
    fun putIntArray(key: String, value: IntArray?)

    fun getIntArrayList(key: String): ArrayList<Int>?
    fun putIntArrayList(key: String, value: ArrayList<Int>?)

    fun getLong(key: String): Long?
    fun putLong(key: String, value: Long?)

    fun getLongArray(key: String): LongArray?
    fun putLongArray(key: String, value: LongArray?)

    fun <P: Parcelable> getParcelable(key: String): P?
    fun <P: Parcelable> putParcelable(key: String, value: P?)

    fun <P: Parcelable> getParcelableArray(key: String): Array<P>?
    fun <P: Parcelable> putParcelableArray(key: String, value: Array<P>?)

    fun <P: Parcelable> getParcelableArrayList(key: String): ArrayList<P>?
    fun <P: Parcelable> putParcelableArrayList(key: String, value: ArrayList<P>?)

    fun <S: Serializable> getSerializable(key: String): S?
    fun <S: Serializable> putSerializable(key: String, value: S?)

    fun getShort(key: String): Short?
    fun putShort(key: String, value: Short?)

    fun getShortArray(key: String): ShortArray?
    fun putShortArray(key: String, value: ShortArray?)

    fun <P: Parcelable> getSparseParcelableArray(key: String): SparseArray<P>?
    fun <P: Parcelable> putSparseParcelableArray(key: String, value: SparseArray<P>?)

    fun getString(key: String): String?
    fun putString(key: String, value: String?)

    fun getStringArray(key: String): Array<String>?
    fun putStringArray(key: String, value: Array<String>?)

    fun getStringArrayList(key: String): ArrayList<String>?
    fun putStringArrayList(key: String, value: ArrayList<String>?)
}

/**
 * BundleBoxer - Boxer implementation backed by Bundle
 */
class BundleBoxer(val bundle: Bundle = Bundle()) : Boxer {

    override fun clear() = bundle.clear()
    override fun containsKey(key: String) = bundle.containsKey(key)
    override fun remove(key: String) = bundle.remove(key)

    override val size: Int get() = bundle.size()
    override val isEmpty: Boolean get() = bundle.isEmpty
    override val keySet: Set<String> get() = bundle.keySet()
    
    override fun getBinder(key: String): IBinder? = bundle.getBinder(key)
    override fun putBinder(key: String, value: IBinder?) = bundle.putBinder(key, value)

    override fun getBoolean(key: String): Boolean? = if (containsKey(key)) bundle.getBoolean(key) else null
    override fun putBoolean(key: String, value: Boolean?) = if (value == null) bundle.remove(key) else bundle.putBoolean(key, value)

    override fun getBooleanArray(key: String): BooleanArray? = bundle.getBooleanArray(key)
    override fun putBooleanArray(key: String, value: BooleanArray?) = bundle.putBooleanArray(key, value)

    override fun getByte(key: String): Byte? = if (containsKey(key)) bundle.getByte(key) else null
    override fun putByte(key: String, value: Byte?) = if (value == null) remove(key) else bundle.putByte(key, value)

    override fun getByteArray(key: String): ByteArray? = bundle.getByteArray(key)
    override fun putByteArray(key: String, value: ByteArray?) = bundle.putByteArray(key, value)

    override fun getChar(key: String): Char? = if (containsKey(key)) bundle.getChar(key) else null
    override fun putChar(key: String, value: Char?) = if (value == null) remove(key) else bundle.putChar(key, value)

    override fun getCharArray(key: String): CharArray? = bundle.getCharArray(key)
    override fun putCharArray(key: String, value: CharArray?) = bundle.putCharArray(key, value)

    override fun getCharSequence(key: String): CharSequence? = bundle.getCharSequence(key)
    override fun putCharSequence(key: String, value: CharSequence?) = bundle.putCharSequence(key, value)

    override fun getCharSequenceArray(key: String): Array<CharSequence>? = bundle.getCharSequenceArray(key)
    override fun putCharSequenceArray(key: String, value: Array<CharSequence>?) = bundle.putCharSequenceArray(key, value)

    override fun getCharSequenceArrayList(key: String): ArrayList<CharSequence>? = bundle.getCharSequenceArrayList(key)
    override fun putCharSequenceArrayList(key: String, value: ArrayList<CharSequence>?) = bundle.putCharSequenceArrayList(key, value)

    override fun getDouble(key: String): Double? = if (containsKey(key)) bundle.getDouble(key) else null
    override fun putDouble(key: String, value: Double?) = if (value == null) remove(key) else bundle.putDouble(key, value)

    override fun getDoubleArray(key: String): DoubleArray? = bundle.getDoubleArray(key)
    override fun putDoubleArray(key: String, value: DoubleArray?) = bundle.putDoubleArray(key, value)

    override fun getFloat(key: String): Float? = if (containsKey(key)) bundle.getFloat(key) else null
    override fun putFloat(key: String, value: Float?) = if (value == null) remove(key) else bundle.putFloat(key, value)

    override fun getFloatArray(key: String): FloatArray? = bundle.getFloatArray(key)
    override fun putFloatArray(key: String, value: FloatArray?) = bundle.putFloatArray(key, value)

    override fun getInt(key: String): Int? = if (containsKey(key)) bundle.getInt(key) else null
    override fun putInt(key: String, value: Int?) = if (value == null) remove(key) else bundle.putInt(key, value)

    override fun getIntArray(key: String): IntArray? = bundle.getIntArray(key)
    override fun putIntArray(key: String, value: IntArray?) = bundle.putIntArray(key, value)

    override fun getIntArrayList(key: String): ArrayList<Int>? = bundle.getIntegerArrayList(key)
    override fun putIntArrayList(key: String, value: ArrayList<Int>?) = bundle.putIntegerArrayList(key, value)

    override fun getLong(key: String): Long? = if (containsKey(key)) bundle.getLong(key) else null
    override fun putLong(key: String, value: Long?) = if (value == null) remove(key) else bundle.putLong(key, value)

    override fun getLongArray(key: String): LongArray? = bundle.getLongArray(key)
    override fun putLongArray(key: String, value: LongArray?) = bundle.putLongArray(key, value)

    override fun <P : Parcelable> getParcelable(key: String): P? = bundle.getParcelable(key)
    override fun <P : Parcelable> putParcelable(key: String, value: P?) = bundle.putParcelable(key, value)

    @Suppress("UNCHECKED_CAST")
    override fun <P : Parcelable> getParcelableArray(key: String): Array<P>? = bundle.getParcelableArray(key) as? Array<P>
    override fun <P : Parcelable> putParcelableArray(key: String, value: Array<P>?) = bundle.putParcelableArray(key, value)

    override fun <P : Parcelable> getParcelableArrayList(key: String): ArrayList<P>? = bundle.getParcelableArrayList(key)
    override fun <P : Parcelable> putParcelableArrayList(key: String, value: ArrayList<P>?) = bundle.putParcelableArrayList(key, value)

    @Suppress("UNCHECKED_CAST")
    override fun <S : Serializable> getSerializable(key: String): S? = bundle.getSerializable(key) as? S
    override fun <S : Serializable> putSerializable(key: String, value: S?) = bundle.putSerializable(key, value)

    override fun getShort(key: String): Short? = if (containsKey(key)) bundle.getShort(key) else null
    override fun putShort(key: String, value: Short?) = if (value == null) remove(key) else bundle.putShort(key, value)

    override fun getShortArray(key: String): ShortArray? = bundle.getShortArray(key)
    override fun putShortArray(key: String, value: ShortArray?) = bundle.putShortArray(key, value)

    override fun <P : Parcelable> getSparseParcelableArray(key: String): SparseArray<P>? = bundle.getSparseParcelableArray(key)
    override fun <P : Parcelable> putSparseParcelableArray(key: String, value: SparseArray<P>?) = bundle.putSparseParcelableArray(key, value)

    override fun getString(key: String): String? = bundle.getString(key)
    override fun putString(key: String, value: String?) = bundle.putString(key, value)

    override fun getStringArray(key: String): Array<String>? = bundle.getStringArray(key)
    override fun putStringArray(key: String, value: Array<String>?) = bundle.putStringArray(key, value)

    override fun getStringArrayList(key: String): ArrayList<String>? = bundle.getStringArrayList(key)
    override fun putStringArrayList(key: String, value: ArrayList<String>?) = bundle.putStringArrayList(key, value)
}

/**
 * MapBoxer - Boxer implementation backed by HashMap
 */
@Suppress("UNCHECKED_CAST")
class MapBoxer(val map: HashMap<String, Any?> = hashMapOf()) : Boxer {

    override fun clear() = map.clear()
    override fun containsKey(key: String) = map.containsKey(key)
    override fun remove(key: String) { map.remove(key) }

    override val size: Int get() = map.size
    override val isEmpty: Boolean get() = map.isEmpty()
    override val keySet: Set<String> get() = map.keys

    override fun getBinder(key: String): IBinder? = map[key] as? Binder
    override fun putBinder(key: String, value: IBinder?) { map[key] = value }

    override fun getBoolean(key: String): Boolean? = map[key] as? Boolean
    override fun putBoolean(key: String, value: Boolean?) { map[key] = value }

    override fun getBooleanArray(key: String): BooleanArray? = map[key] as? BooleanArray
    override fun putBooleanArray(key: String, value: BooleanArray?) { map[key] = value }

    override fun getByte(key: String): Byte? = map[key] as? Byte
    override fun putByte(key: String, value: Byte?) { map[key] = value }

    override fun getByteArray(key: String): ByteArray? = map[key] as? ByteArray
    override fun putByteArray(key: String, value: ByteArray?) { map[key] = value }

    override fun getChar(key: String): Char? = map[key] as? Char
    override fun putChar(key: String, value: Char?) { map[key] = value }

    override fun getCharArray(key: String): CharArray? = map[key] as? CharArray
    override fun putCharArray(key: String, value: CharArray?) { map[key] = value }

    override fun getCharSequence(key: String): CharSequence? = map[key] as? CharSequence
    override fun putCharSequence(key: String, value: CharSequence?) { map[key] = value }

    override fun getCharSequenceArray(key: String): Array<CharSequence>? = map[key] as? Array<CharSequence>
    override fun putCharSequenceArray(key: String, value: Array<CharSequence>?) { map[key] = value }

    override fun getCharSequenceArrayList(key: String): ArrayList<CharSequence>? = map[key] as? ArrayList<CharSequence>
    override fun putCharSequenceArrayList(key: String, value: ArrayList<CharSequence>?) { map[key] = value }

    override fun getDouble(key: String): Double? = map[key] as? Double
    override fun putDouble(key: String, value: Double?) { map[key] = value }

    override fun getDoubleArray(key: String): DoubleArray? = map[key] as? DoubleArray
    override fun putDoubleArray(key: String, value: DoubleArray?) { map[key] = value }

    override fun getFloat(key: String): Float? = map[key] as? Float
    override fun putFloat(key: String, value: Float?) { map[key] = value }

    override fun getFloatArray(key: String): FloatArray? = map[key] as? FloatArray
    override fun putFloatArray(key: String, value: FloatArray?) { map[key] = value }

    override fun getInt(key: String): Int? = map[key] as? Int
    override fun putInt(key: String, value: Int?) { map[key] = value }

    override fun getIntArray(key: String): IntArray? = map[key] as? IntArray
    override fun putIntArray(key: String, value: IntArray?) { map[key] = value }

    override fun getIntArrayList(key: String): ArrayList<Int>? = map[key] as? ArrayList<Int>
    override fun putIntArrayList(key: String, value: ArrayList<Int>?) { map[key] = value }

    override fun getLong(key: String): Long? = map[key] as? Long
    override fun putLong(key: String, value: Long?) { map[key] = value }

    override fun getLongArray(key: String): LongArray? = map[key] as? LongArray
    override fun putLongArray(key: String, value: LongArray?) { map[key] = value }

    override fun <P : Parcelable> getParcelable(key: String): P? = map[key] as? P
    override fun <P : Parcelable> putParcelable(key: String, value: P?) { map[key] = value }

    @Suppress("UNCHECKED_CAST")
    override fun <P : Parcelable> getParcelableArray(key: String): Array<P>? = map[key] as? Array<P>
    override fun <P : Parcelable> putParcelableArray(key: String, value: Array<P>?) { map[key] = value }

    override fun <P : Parcelable> getParcelableArrayList(key: String): ArrayList<P>? = map[key] as? ArrayList<P>
    override fun <P : Parcelable> putParcelableArrayList(key: String, value: ArrayList<P>?) { map[key] = value }

    @Suppress("UNCHECKED_CAST")
    override fun <S : Serializable> getSerializable(key: String): S? = map[key] as? S
    override fun <S : Serializable> putSerializable(key: String, value: S?) { map[key] = value }

    override fun getShort(key: String): Short? = map[key] as? Short
    override fun putShort(key: String, value: Short?) { map[key] = value }

    override fun getShortArray(key: String): ShortArray? = map[key] as? ShortArray
    override fun putShortArray(key: String, value: ShortArray?) { map[key] = value }

    override fun <P : Parcelable> getSparseParcelableArray(key: String): SparseArray<P>? = map[key] as? SparseArray<P>
    override fun <P : Parcelable> putSparseParcelableArray(key: String, value: SparseArray<P>?) { map[key] = value }

    override fun getString(key: String): String? = map[key] as? String
    override fun putString(key: String, value: String?) { map[key] = value }

    override fun getStringArray(key: String): Array<String>? = map[key] as? Array<String>
    override fun putStringArray(key: String, value: Array<String>?) { map[key] = value }

    override fun getStringArrayList(key: String): ArrayList<String>? = map[key] as? ArrayList<String>
    override fun putStringArrayList(key: String, value: ArrayList<String>?) { map[key] = value }
}

/** Gets the fragment's existing args bundle if it exists, or creates and attaches a new bundle if it doesn't */
val Fragment.nonNullArgs: Bundle
    get() = arguments ?: Bundle().apply { this@nonNullArgs.arguments = this }

val Fragment.boxer get() = BundleBoxer(nonNullArgs)
val Activity.boxer get() = BundleBoxer(intent?.extras ?: Bundle())