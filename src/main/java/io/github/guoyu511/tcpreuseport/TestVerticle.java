package io.github.guoyu511.tcpreuseport;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.impl.HttpServerImpl;

/**
 * Created by guoyu03@kuaishou.com on 2018/3/28
 */
public class TestVerticle extends AbstractVerticle {

  public static void main(String... args) throws InterruptedException {
    System.out.println("Init vert.x");
    Vertx vertx = Vertx.vertx(new VertxOptions()
      .setPreferNativeTransport(true));
    vertx.deployVerticle(TestVerticle.class.getName(),
      new DeploymentOptions()
        .setInstances(Runtime.getRuntime().availableProcessors() * 2));
    System.out.println("Using eventloop - " +
      vertx.nettyEventLoopGroup().getClass().getName()
    );
  }

  @Override
  public void start() throws Exception {
    HttpServerImpl server = (HttpServerImpl)vertx
      .createHttpServer(new HttpServerOptions()
        .setReuseAddress(true)
        .setReusePort(true)
      )
      .requestHandler((req) -> {
        req.bodyHandler((body) -> {
          req.response().end(body);
        });
      })
      .listen(9999);
    System.out.println("http server listen at " + server.actualPort());
  }

}
