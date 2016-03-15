package io.vertx.ext.msa.abcd;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * C publishes the C REST services.
 * <p>
 * No circuit-breaker involved, as it should be managed in the browser.
 * No discovery involved, as it should be managed in the browser.
 */
public class D extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);

    // Enable Cors
    router.route().handler(CorsHandler.create("*")
        .allowedMethod(HttpMethod.GET)
        .allowedHeader("Content-Type"));

    // Publish the B service
    router.route(HttpMethod.GET, "/").handler(context -> {
      context.response().end("Aloha " + context.request().getParam("name"));
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}