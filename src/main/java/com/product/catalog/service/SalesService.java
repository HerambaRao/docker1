package com.product.catalog.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dao.SalesRepo;
import com.product.catalog.ents.Sales;
import com.product.catalog.pojo.BarChartData;
import com.product.catalog.pojo.SalesBarChart;
import com.product.catalog.pojo.SalesDto;

@Service
public class SalesService {

	@Autowired
	private SalesRepo salesRepo;

	public SalesDto saveSalesService(SalesDto dto) {
		Sales sales = salesRepo.save(getEntity(dto));
		return getDto(sales);
	}

	public SalesDto getSalesById(Long salesId) {
		Sales sales = salesRepo.findById(salesId).get();
		return getDto(sales);
	}

	public void deleteSalesById(Long salesId) {

		salesRepo.deleteById(salesId);
	}

	public List<SalesDto> getAllSales() {
		List<SalesDto> list = new ArrayList<SalesDto>();
		salesRepo.findAll().forEach(sales -> {
			list.add(getDto(sales));
		});
		return list;
	}

	public Sales getEntity(SalesDto dto) {
		Sales sales = new Sales();

		sales.setCount(dto.getCount());
		sales.setCountry(dto.getCountry());
		sales.setId(dto.getId());
		sales.setProductId(dto.getProductId());
		sales.setRegion(dto.getRegion());
		return sales;
	}

	public SalesDto getDto(Sales sales) {
		SalesDto dto = new SalesDto();
		dto.setCount(sales.getCount());
		dto.setCountry(sales.getCountry());
		dto.setId(sales.getId());
		dto.setProductId(sales.getProductId());
		dto.setRegion(sales.getRegion());
		dto.setProductName(ProductService.productMap.get(sales.getProductId()));
		return dto;
	}
	
	public SalesBarChart getSalesChart() {
		SalesBarChart salesBarChart = new SalesBarChart();
		List<SalesDto> list = getAllSales();
		//Map<String, List<Long>> barData = new HashMap<String, List<Long>>();
		List<String> barChartLabels = new ArrayList<>();
		List<BarChartData> barList = new ArrayList<BarChartData>();
		Map<String, List<SalesDto>> map = new HashMap<>();
		list.forEach(sales -> {
			
			barChartLabels.add(sales.getRegion());
			if(map.containsKey(sales.getProductName())) {
				List<SalesDto> list1 = map.get(sales.getProductName());
				list1.add(sales);
				map.put(sales.getProductName(), list1);
			} else {
				List<SalesDto> list2 = new ArrayList<SalesDto>();
				list2.add(sales);
				map.put(sales.getProductName(),list2);
			}
		});
		
		Set<Entry<String,List<SalesDto>>> set = map.entrySet();
		
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
	
	
  public Long[] getChartArray(List<String> barChartLabels, List<SalesDto> salesList) {
		
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
