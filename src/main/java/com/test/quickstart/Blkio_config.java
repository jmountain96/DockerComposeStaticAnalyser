package com.test.quickstart;

public class Blkio_config {
	private int weight;
	private Blkio_config_option[] weight_device;
	private Blkio_config_option[] device_read_bps;
	private Blkio_config_option[] device_write_bps;
	private Blkio_config_option[] device_read_iops;
	private Blkio_config_option[] device_write_iops;
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Blkio_config_option[] getWeight_device() {
		return weight_device;
	}
	public void setWeight_device(Blkio_config_option[] weight_device) {
		this.weight_device = weight_device;
	}
	public Blkio_config_option[] getDevice_read_bps() {
		return device_read_bps;
	}
	public void setDevice_read_bps(Blkio_config_option[] device_read_bps) {
		this.device_read_bps = device_read_bps;
	}
	public Blkio_config_option[] getDevice_write_bps() {
		return device_write_bps;
	}
	public void setDevice_write_bps(Blkio_config_option[] device_write_bps) {
		this.device_write_bps = device_write_bps;
	}
	public Blkio_config_option[] getDevice_read_iops() {
		return device_read_iops;
	}
	public void setDevice_read_iops(Blkio_config_option[] device_read_iops) {
		this.device_read_iops = device_read_iops;
	}
	public Blkio_config_option[] getDevice_write_iops() {
		return device_write_iops;
	}
	public void setDevice_write_iops(Blkio_config_option[] device_write_iops) {
		this.device_write_iops = device_write_iops;
	}
	
}
