package test;

import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class Vert {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vertx vertx = Vertx.factory.vertx();
		vertx.deployVerticle(new ServerExample(), new Handler<AsyncResult<String>>() {
		    @Override
		    public void handle(AsyncResult<String> stringAsyncResult) {
		        System.out.println("BasicVerticle deployment complete");
		        }
		    });
	}
	
}
