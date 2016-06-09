package com.egen.alerts.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.egen.alerts.main.entity.Alert;

@Service
public interface AlertService {

	public List<Alert> getAlertsbyTime(long from, long to);

	public List<Alert> getAlerts();

	public Alert create(Alert alert);

}
