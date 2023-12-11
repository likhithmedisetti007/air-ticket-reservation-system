package com.likhith.demo.air.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.likhith.demo.air.document.UserRegistry;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRegistryRepository extends ReactiveMongoRepository<UserRegistry, String> {

	Mono<UserRegistry> findByIdentityTypeAndIdentityProof(String identityType, String identityProof);

	Flux<UserRegistry> findByVerifiedAndActive(boolean verified, boolean active);

}