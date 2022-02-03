package com.mettle.userfeaturemanager.repository;

import com.mettle.userfeaturemanager.model.FeatureScope;
import com.mettle.userfeaturemanager.model.datastore.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findAllByScope(FeatureScope scope);
}