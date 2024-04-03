package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.DropDownList;
import com.rays.common.ORSResponse;
import com.rays.dto.AttechamentDTO;
import com.rays.dto.CarDTO;
import com.rays.form.CarForm;
import com.rays.service.AttechamentServiceInt;
import com.rays.service.CarServiceInt;

@RestController
@RequestMapping(value = "car")
public class CarCtl {

	@Autowired
	CarServiceInt service;

	@Autowired
	AttechamentServiceInt attechamentservice;

	@PostMapping("save")
	public ORSResponse add(@RequestBody CarForm form) {

		ORSResponse res = new ORSResponse(true);

		if (!res.isSuccess()) {
			return res;
		}
		CarDTO dto = new CarDTO();
		dto.setId(form.getId());
		dto.setOwnerName(form.getOwnerName());
		dto.setModel(form.getModel());
		dto.setCarName(form.getCarName());
		dto.setPrice(form.getPrice());
		dto.setPurchaseDate(form.getPurchaseDate());

		if (dto.getId() != null && dto.getId() > 0) {
			service.update(dto);
			res.addMessage("data update");
		} else {
			service.add(dto);
			res.addMessage("data add");
		}
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@RequestBody @PathVariable long id) {
		ORSResponse res = new ORSResponse(true);
		service.delete(id);
		res.addMessage("data delete");
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@RequestBody @PathVariable long id) {
		ORSResponse res = new ORSResponse(true);

		CarDTO dto = service.findbyid(id);

		if (dto != null) {
			res.addData(dto);
		}
		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody CarForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse(true);
		CarDTO dto = new CarDTO();
		dto.setId(form.getId());
		dto.setPurchaseDate(form.getPurchaseDate());
		dto.setCarName(form.getCarName());
		List list = service.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("NO RECORD FOUND...!!!");
		} else {
			res.addData(list);
		}

		return res;

	}

	@PostMapping("preload")
	public ORSResponse preload(@RequestBody CarForm form) {
		ORSResponse res = new ORSResponse(true);
		CarDTO dto = new CarDTO();
		dto.setPurchaseDate(form.getPurchaseDate());
		List<DropDownList> rolelist = service.search(dto, 0, 0);

		res.addResult("rolelist", rolelist);

		return res;

	}

	@PostMapping("profilePic/{carId}")
	public ORSResponse uploadPic(@PathVariable Long carId, @RequestParam("file") MultipartFile file,
			HttpServletResponse response) {

		ORSResponse res = new ORSResponse(true);

		AttechamentDTO attachmentDto = new AttechamentDTO(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(carId);

		CarDTO carDto = service.findbyid(carId);

		if (carDto.getImageId() != null && carDto.getImageId() > 0) {

			attachmentDto.setId(carDto.getImageId());
		}

		Long imageId = attechamentservice.add(attachmentDto);

		if (carDto.getImageId() == null) {

			carDto.setImageId(imageId);

			service.update(carDto);
		}

		res.addResult("imageId", imageId);

		return res;
	}

	@GetMapping("profilePic/{carId}")
	public @ResponseBody void downloadPic(@PathVariable Long carId, HttpServletResponse response) {

		try {

			CarDTO carDto = service.findbyid(carId);
			System.out.println("immage id >>>>>" + carDto.getImageId());
			AttechamentDTO attachmentDTO = null;

			if (carDto != null) {
				attachmentDTO = attechamentservice.findbyid(carDto.getImageId());
			}

			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found>>>>>>>>>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
