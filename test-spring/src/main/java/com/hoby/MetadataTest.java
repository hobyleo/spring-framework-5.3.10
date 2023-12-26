package com.hoby;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hoby
 * @since 2023-12-26
 */
public class MetadataTest {

	public static void main(String[] args) throws IOException {

		// MetadataReader表示类的元数据读取器，默认实现类为SimpleMetadataReader
		// SimpleMetadataReader去解析类时，使用的ASM技术
		SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();

		// 构造一个MetadataReader
		MetadataReader metadataReader = simpleMetadataReaderFactory.getMetadataReader("com.hoby.service.UserService");

		// 得到一个ClassMetadata，并获取了类名
		ClassMetadata classMetadata = metadataReader.getClassMetadata();

		System.out.println(classMetadata.getClassName());

		// 获取一个AnnotationMetadata，并获取类上的注解信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		for (String annotationType : annotationMetadata.getAnnotationTypes()) {
			System.out.println(annotationType);
		}

		// 是否包含某个注解
		System.out.println(annotationMetadata.hasAnnotation(Component.class.getName()));
		// 是否包含某个元注解
		System.out.println(annotationMetadata.hasMetaAnnotation(Component.class.getName()));

	}
}
