package example;

import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.predicate.Predicates;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;

class ExampleHandlerProvider implements HandlerProvider {

  HttpHandler getHandler() {
    return Handlers.routing()
            .add(Methods.GET, "/baz", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("baz")
    })
            .add(Methods.GET, "/baz/{foo}", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("baz-path" + exchange.getQueryParameters().get("foo"))
    })
            .add(Methods.GET, "/bar", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("get bar")
    })
            .add(Methods.PUT, "/bar", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("put bar")
    })
            .add(Methods.POST, "/bar", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("post bar")
    })
            .add(Methods.DELETE, "/bar", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("delete bar")
    })
            .add(Methods.GET, "/wild/{test}/*", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("wild:" + exchange.getQueryParameters().get("test") + ":" + exchange.getQueryParameters().get("*"))
    })
            .add(Methods.GET, "/foo", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("foo")
    })
            .add(Methods.GET, "/foo", Predicates.parse("contains[value=%{i,SomeHeader},search='special'] "), {HttpServerExchange exchange ->
      exchange.getResponseSender().send("special foo")
    })
            .add(Methods.POST, "/foo", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("posted foo")
    })
            .add(Methods.GET, "/foo/{bar}", {HttpServerExchange exchange ->
      exchange.getResponseSender().send("foo-path" + exchange.getQueryParameters().get("bar"))
    })
  }
}