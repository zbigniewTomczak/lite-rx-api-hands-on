package io.pivotal.literx;

import java.time.Duration;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/core/docs/api/reactor/core/publisher/Mono.html">Mono Javadoc</a>
 */
public class Part02Mono {

//========================================================================================

	@Test
	public void empty() {
		Mono<String> mono = emptyMono();
		StepVerifier.create(mono)
				.verifyComplete();
	}

	Mono<String> emptyMono() {
		return Mono.empty();
	}

//========================================================================================

	@Test
	public void noSignal() {
		Mono<String> mono = monoWithNoSignal();
		StepVerifier
				.create(mono)
				.expectSubscription()
				.expectNoEvent(Duration.ofSeconds(1))
				.thenCancel()
				.verify();
	}

	Mono<String> monoWithNoSignal() {
		return Mono.never();
	}

//========================================================================================

	@Test
	public void fromValue() {
		Mono<String> mono = fooMono();
		StepVerifier.create(mono)
				.expectNext("foo")
				.verifyComplete();
	}

	Mono<String> fooMono() {
		return Mono.just("foo");
	}

//========================================================================================

	@Test
	public void error() {
		Mono<String> mono = errorMono();
		StepVerifier.create(mono)
				.verifyError(IllegalStateException.class);
	}

	Mono<String> errorMono() {
		return Mono.error(new IllegalStateException());
	}

}
