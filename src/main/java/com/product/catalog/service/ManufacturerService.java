package com.product.catalog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dao.ManufacturerRepo;
import com.product.catalog.ents.Manufacturer;
import com.product.catalog.pojo.BarChartData;
import com.product.catalog.pojo.ManufacturerDto;
import com.product.catalog.pojo.SalesBarChart;
import com.product.catalog.pojo.SalesDto;

@Service
public class ManufacturerService {

	@Autowired
	private ManufacturerRepo manufacturerRepo;

	public ManufacturerDto saveManufacturer(ManufacturerDto manufacturerDto) {
		Manufacturer manufacturer = manufacturerRepo.save(getEntity(manufacturerDto));
		return getDto(manufacturer);
	}

	public ManufacturerDto getManufacturerById(Long manufacturerId) {
		Manufacturer manufacturer = manufacturerRepo.findById(manufacturerId).get();
		return getDto(manufacturer);
	}

	public void deleteManufacturerById(Long manufacturerId) {

		manufacturerRepo.deleteById(manufacturerId);
	}

	public List<ManufacturerDto> getAllManufacturers() {
		List<ManufacturerDto> list = new ArrayList<ManufacturerDto>();
		manufacturerRepo.findAll().forEach(manufacture -> {
			list.add(getDto(manufacture));
		});
		return list;
	}

	public Manufacturer getEntity(ManufacturerDto dto) {
		Manufacturer manufacturer = new Manufacturer();

		manufacturer.setCount(dto.getCount());
		manufacturer.setCountry(dto.getCountry());
		manufacturer.setId(dto.getId());
		manufacturer.setProductId(dto.getProductId());
		manufacturer.setRegion(dto.getRegion());
		return manufacturer;
	}

	public ManufacturerDto getDto(Manufacturer manufacturer) {
		ManufacturerDto dto = new ManufacturerDto();
		dto.setCount(manufacturer.getCount());
		dto.setCountry(manufacturer.getCountry());
		dto.setId(manufacturer.getId());
		dto.setProductId(manufacturer.getProductId());
		dto.setRegion(manufacturer.getRegion());
		dto.setProductName(ProductService.productMap.get(manufacturer.getProductId()));
		return dto;
	}
	
	
	public SalesBarChart getManufacturerChart() {
		SalesBarChart salesBarChart = new SalesBarChart();
		List<ManufacturerDto> list = getAllManufacturers();
		List<String> barChartLabels = new ArrayList<>();
		List<BarChartData> barList = new ArrayList<BarChartData>();
		Map<String, List<ManufacturerDto>> map = new HashMap<>();
		list.forEach(sales -> {
			barChartLabels.add(sales.getRegion());
			if(map.containsKey(sales.getProductName())) {
				List<ManufacturerDto> list1 = map.get(sales.getProductName());
				list1.add(sales);
				map.put(sales.getProductName(), list1);
			} else {
				List<ManufacturerDto> list2 = new ArrayList<ManufacturerDto>();
				list2.add(sales);
				map.put(sales.getProductName(),list2);
			}
		});
		
		Set<Entry<String,List<ManufacturerDto>>> set = map.entrySet();
		
		set.forEach(entry->{
			BarChartData barData = new BarChartData();
		//	System.out.println("Key "+ entry.getKey());
		//	System.out.println("Value " + entry.getValue().size());
			barData.setLabel(entry.getKey());
			barData.setData(getChartArray(barChartLabels, entry.getValue()));
			barList.add(barData);
			
		});
		salesBarChart.setBarChartLabels(barChartLabels.stream().toArray(String[]::new));
		salesBarChart.setBarChartDatas(barList.stream().toArray(BarChartData[]::new));
		
		return salesBarChart;
	}
	
	
  public Long[] getChartArray(List<String> barChartLabels, List<ManufacturerDto> salesList) {
		
		List<Long> dataArray = new ArrayList<>(Arrays.asList(new Long[barChartLabels.size()]));
		Collections.fill(dataArray, new Long(0));
		System.out.println(dataArray);
		Map<String, List<Long>> map = new HashMap<String, List<Long>>();
		salesList.forEach(sales-> {
			if(barChartLabels.contains(sales.getRegion())) {
			String region = sales.getRegion();
			dataArray.set(barChartLabels.indexOf(region), sales.getCount());
			}
		});
		
//		dataArray.forEach(x->{
//			System.out.print(x + " "+ ",");
//		});
//		 System.out.println();
	return dataArray.stream().toArray(Long[]::new);
  }

}
