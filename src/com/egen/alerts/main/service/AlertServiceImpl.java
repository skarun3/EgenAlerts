package com.egen.alerts.main.service;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.alerts.main.entity.Alert;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private Datastore datastore;

	@Override
	public Alert create(Alert alert) {
		datastore.save(alert);
		return alert;
	}

	@Override
	public List<Alert> getAlertsbyTime(long from, long to) {

		return datastore.createQuery(Alert.class).filter("timestamp >=", from).filter("timestamp <=", to).asList();
	}

	@Override
	public List<Alert> getAlerts() {
		return datastore.createQuery(Alert.class).asList();
	}

}
