package com.the_chance.jokes.data.remote.gemini.helper

import com.the_chance.jokes.domain.model.JokeCommand
import org.springframework.stereotype.Component

@Component
class PromptBuilder {
    fun build(request: JokeCommand): String {
        val formattedRules = request.rules
            .mapIndexed { index, rule -> "${index + 1}. $rule" }
            .joinToString("\n")

        return """
            ${request.persona}
            
            اكتب نكتة باللهجة العامية ${request.dialect} عن كلمة "${request.word}".
            
            اسلوب النكتة:
            $formattedRules
            
            Seed: ${request.seed}
            
            ممنوع:
            - شرح
            - مقدمة
            - أي كلام خارج النكتة
            
            اكتب النكتة مباشرة.
        """.trimIndent()
    }
}