package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class ReactiveGreetingResource {

    private final ReactiveMailer mailer;

    public ReactiveGreetingResource(ReactiveMailer mailer) {
        this.mailer = mailer;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        return mailer.send(
                new Mail()
                        .setFrom("test<svc_smtpint@nte.no>")
                        .setTo(List.of("marvin.lillehaug@nte.no"))
                        .setSubject("test email")
                        .setText("This is an email")
        ).map(unused -> "Mail sendt");
    }
}
