package com.codersee.kotlindsl.config

import com.codersee.kotlindsl.config.DataSource.devDataSource
import com.codersee.kotlindsl.config.DataSource.prodDataSource
import com.codersee.kotlindsl.handler.UserHandler
import com.codersee.kotlindsl.repository.UserCrudRepository
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

class BeansConfig : ApplicationContextInitializer<GenericApplicationContext> {

  override fun initialize(context: GenericApplicationContext) =
    beans.initialize(context)

}

val beans = beans {

  val someVariable = env.systemEnvironment["SOME_VARIABLE"]

  profile("dev") {
    bean {
      UserCrudRepository(devDataSource)
    }
  }

  profile("prod") {
    bean {
      UserCrudRepository(prodDataSource)
    }
  }

  bean<UserHandler>(
    name = "userHandler",
    scope = BeanDefinitionDsl.Scope.SINGLETON,
    isLazyInit = true,
    isPrimary = true,
    isAutowireCandidate = true,
    initMethodName = "",
    destroyMethodName = "",
    description = "description",
    role = BeanDefinitionDsl.Role.APPLICATION
  )

//  bean("myHandlerBean") {
//    UserHandler(ref())
//  }
//  bean {
//    appRouter(
//      ref<UserHandler>("myHandlerBean")
//    )
//  }

//  bean {
//    appRouter(ref())
//  }
//
  bean(::appRouter)
}

