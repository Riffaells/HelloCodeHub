package com.riffaells.hellocodehub.domain.model

import androidx.compose.runtime.Immutable
import hellocodehub.composeapp.generated.resources.*
import hellocodehub.composeapp.generated.resources.Res
import hellocodehub.composeapp.generated.resources.paradigm_declarative
import hellocodehub.composeapp.generated.resources.paradigm_imperative
import hellocodehub.composeapp.generated.resources.paradigm_multi
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Immutable
@Serializable
data class Paradigms(val paradigms: List<Int>) {


    fun getStringParadigms() = getParadigms(paradigms)


    companion object {
        fun getParadigms(paradigms: List<Int>): List<StringResource> {
            return paradigms.map { paradigm ->
                getParadigm(paradigm)
            }
        }

        fun getParadigm(paradigm: Int): StringResource =
            when (ParadigmsEnum.values().first { it.typeLang == paradigm }) {
                ParadigmsEnum.ASSEMBLY -> Res.string.paradigm_assembly
                ParadigmsEnum.BLOCK_STRUCTURED -> Res.string.paradigm_block
                ParadigmsEnum.CROSS_PLATFORM -> Res.string.paradigm_cross_platform
                ParadigmsEnum.DATA_ORIENTED -> Res.string.paradigm_data_oriented
                ParadigmsEnum.DECLARATIVE -> Res.string.paradigm_declarative
                ParadigmsEnum.FUNCTIONAL -> Res.string.paradigm_functional
                ParadigmsEnum.GENERIC -> Res.string.paradigm_generic
                ParadigmsEnum.HIGH_LEVEL -> Res.string.paradigm_high_level
                ParadigmsEnum.IMPERATIVE -> Res.string.paradigm_imperative
                ParadigmsEnum.LOGIC -> Res.string.paradigm_logic
                ParadigmsEnum.LOW_LEVEL -> Res.string.paradigm_low_level
                ParadigmsEnum.MARKUP -> Res.string.paradigm_markup
                ParadigmsEnum.MULTI_PARADIGM -> Res.string.paradigm_multi
                ParadigmsEnum.OBJECT_ORIENTED -> Res.string.paradigm_object_oriented
                ParadigmsEnum.PARALLEL -> Res.string.paradigm_parallel
                ParadigmsEnum.PROCEDURAL -> Res.string.paradigm_procedural
                ParadigmsEnum.REFLECTIVE -> Res.string.paradigm_reflective
                ParadigmsEnum.SCRIPTING -> Res.string.paradigm_scripting
            }


    }

}


@Immutable
@Serializable
enum class ParadigmsEnum(val typeLang: Int) {
    ASSEMBLY(1),
    BLOCK_STRUCTURED(2),
    CROSS_PLATFORM(3),
    DATA_ORIENTED(4),
    DECLARATIVE(5),
    FUNCTIONAL(6),
    GENERIC(7),
    HIGH_LEVEL(8),
    IMPERATIVE(9),
    LOGIC(10),
    LOW_LEVEL(11),
    MARKUP(12),
    MULTI_PARADIGM(13),
    OBJECT_ORIENTED(14),
    PARALLEL(15),
    PROCEDURAL(16),
    REFLECTIVE(17),
    SCRIPTING(18),

}


