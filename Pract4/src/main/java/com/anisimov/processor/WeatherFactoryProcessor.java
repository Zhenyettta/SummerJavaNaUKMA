package com.anisimov.processor;

import com.anisimov.annotation.WeatherFactory;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("com.anisimov.annotation.WeatherFactory")
public class WeatherFactoryProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(WeatherFactory.class)) {
            String className = element.getSimpleName() + "Factory";
            String packageName = processingEnv.getElementUtils().getPackageOf(element).toString();

            MethodSpec createMethod = MethodSpec.methodBuilder("create")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(ClassName.get(packageName, element.getSimpleName().toString()))
                    .addStatement("return new $L()", element.getSimpleName().toString())
                    .build();

            TypeSpec factoryClass = TypeSpec.classBuilder(className)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(createMethod)
                    .build();

            JavaFile javaFile = JavaFile.builder(packageName, factoryClass)
                    .build();

            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
