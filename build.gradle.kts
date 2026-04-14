import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.openapi.generator") version "7.5.0"
	id("idea")
}

group = "entertainment.damir"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Для Jakarta EE (Spring Boot 3.x)
	implementation("jakarta.validation:jakarta.validation-api:3.1.1")

	// Опционально: для удобной работы с датами
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}

// ===== НАСТРОЙКА OPENAPI GENERATOR =====
val generateApi by tasks.registering(GenerateTask::class) {
	generatorName.set("spring")
	inputSpec.set("$projectDir/src/main/resources/openapi/api.yaml")
	outputDir.set("$buildDir/generated")
	apiPackage.set("entertainment/damir/summer.api")
	modelPackage.set("entertainment/damir/summer.model")
	invokerPackage.set("entertainment/damir/summer.invoker")
	configOptions.set(
		mapOf(
			"useSpringBoot3" to "true",
			"useJakartaEe" to "true",
			"interfaceOnly" to "true",
			"skipDefaultInterface" to "true",
			"openApiNullable" to "false",
			"dateLibrary" to "java8",
			"sourceFolder" to "src/main/java"
		)
	)
}

// Подключаем сгенерированные исходники
sourceSets {
	main {
		java {
			srcDir("$buildDir/generated/src/main/java")
		}
	}
}

// Задача для очистки
tasks.register<Delete>("cleanOpenApi") {
	delete("$buildDir/generated")
}

// Настройка зависимостей задач
tasks["cleanOpenApi"].dependsOn(tasks.named("clean"))
generateApi.get().dependsOn("cleanOpenApi")
tasks["compileJava"].dependsOn(generateApi)

// Чтобы IDEA увидела сгенерированные файлы
idea {
	module {
		generatedSourceDirs.add(file("$buildDir/generated/src/main/java"))
	}
}
