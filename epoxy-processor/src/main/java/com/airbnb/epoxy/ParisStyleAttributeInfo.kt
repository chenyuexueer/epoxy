package com.airbnb.epoxy

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import javax.lang.model.element.ElementKind
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

const val BUILDER_STYLE_METHOD_PREFIX = "add"
const val PARIS_DEFAULT_STYLE_CONSTANT_NAME = "DEFAULT_PARIS_STYLE"
const val PARIS_STYLE_ATTR_NAME = "style"


internal class ParisStyleAttributeInfo(
        modelInfo: ModelViewInfo,
        elements: Elements,
        val types: Types,
        packageName: String,
        styleBuilderClassName: ClassName,
        styleBuilderElement: TypeMirror
) : AttributeInfo() {

    val styleNames: List<String>
    val styleApplierClass: ClassName
    val styleBuilderClass: ClassName

    init {
        fieldName = PARIS_STYLE_ATTR_NAME
        modelName = modelInfo.generatedName.simpleName()
        modelPackageName = packageName
        typeMirror = Utils.getTypeMirror(ClassNames.PARIS_STYLE, elements, types)
        styleBuilderClass = styleBuilderClassName
        ignoreRequireHashCode = true
        isGenerated = true
        useInHash = true
        isNullable = false
        styleNames = findStyleNames(styleBuilderElement)

        // the builder is nested in the style applier class
        styleApplierClass = styleBuilderClassName.topLevelClassName()

        codeToSetDefault.explicit = CodeBlock.of(PARIS_DEFAULT_STYLE_CONSTANT_NAME)
    }

    private fun findStyleNames(typeMirror: TypeMirror): List<String> {
        return types.asElement(typeMirror)
                .enclosedElements
                .filter {
                    it.kind == ElementKind.METHOD
                            && it.simpleName.startsWith(BUILDER_STYLE_METHOD_PREFIX)
                }
                .map {
                    it.simpleName
                            .toString()
                            .removePrefix(BUILDER_STYLE_METHOD_PREFIX)
                            .lowerCaseFirstLetter()
                }
    }

}