
// bcoz we are at cross origin side, baseUri ends with /cors 
// as per the @Path config on the JaxRs resource 
var baseUri = "http://localhost:8081/jaxrsWebapp01/rest/cors";

// fyi : For same origin baseUri was "http://localhost:8081/jaxrsWebapp01/rest";

function getEmp(id, responseHandler) {
	console.log('0) getEmp() id=' + id);
	let result = null;
	if (id != null && id != "") {
		const uri = `${baseUri}/employeeService/get/${id}`;
		console.log('1) getEmp() fetch start');
		fetch(uri)
			.then((response) => {
				if (!response.ok) {
					throw new Error(`HTTP error, status = ${response.status}`);
				}
				console.log('2) getEmp() got json response from server : ' + response);
				return response.json();
			})
			.then((data) => {
				// document.write(`<h2> ${data.empno}, ${data.name}, ${data.salary} </h2>`);
				console.log('3) getEmp() json converted to js object : ' + data);
				result = `${data.empno}, ${data.name}, ${data.salary}`;
				responseHandler(result);
			}).catch(function(error) {
				console.warn('Something went wrong.', error);
				//document.write(`<h2> No data found </h2>`);
				result = "No data found";
				responseHandler(result);
			});

		console.log('4) getEmp() fetch() over');
	}
	else {
		result = "empno is missing..Cannot send request without id !"
		responseHandler(result);
	}
	console.log('5) response will render after fetch(). returining null or error if any')
	return result;
}


function saveEmp(empData, responseHandler) {
	let result = null;
	const jsonString = JSON.stringify(empData);
	const uri = `${baseUri}/employeeService/dml/save`;
	console.log("1) saveEmp() fetch start");
	fetch(uri, {
		method: 'POST',
		body: jsonString,
		headers: {
			'Content-type': 'application/json; charset=UTF-8'
		}
	})
		.then((response) => {

			if (!response.ok) {
				throw new Error(`HTTP error, status = ${response.status}`);
			}
			console.log("2) saveEmp() got json response from server : " + response);
			return response.text();
		})
		.then((data) => {
			console.log('3) saveEmp() json converted to js object : ' + data);
			// document.write(`<h2> Saved emp : ${data} </h2>`);
			result = data;
			responseHandler(result);
		}).catch(function(error) {
			console.warn('Something went wrong.', error);
			//document.write(`<h2> ${error} </h2>`);
			result = error;
			responseHandler(result);
		});
	console.log('4) response will render after fetch(). returining null or error if any')
	return result;
}

function deleteEmp(id,responseHandler) {
	let result = null;
	if (id != null && id != "") {
		const uri = `${baseUri}/employeeService/dml/delete/${id}`;
		console.log("1) deleteEmp() fetch start");

		fetch(uri, { method: 'DELETE' })
			.then((response) => {
				console.log(response)
				if (!response.ok) {
					throw new Error(`HTTP error, status = ${response.status}`);
				}
				console.log("2) deleteEmp() got json response from server : " + response);
				return response.text();
			})
			.then((data) => {
				//document.write(`<h2> data=${data} </h2>`);
				console.log('3) deleteEmp() json converted to js object : ' + data);
				result = data;
				responseHandler(result);
			}).catch(function(error) {
				console.warn('Something went wrong.', error);
				//document.write(`<h2> ${error} </h2>`);
				result = error;
				responseHandler(result);
			});
	}
	console.log('4) response will render after fetch(). returining null or error if any')
	return result;
}


function updateEmp(empData, responseHandler) {
	let result = null;

	if (empData) {
		const jsonString = JSON.stringify(empData);
		const uri = `${baseUri}/employeeService/dml/update`;

		console.log("1) updateEmp() fetch start, uri="+uri);

		fetch(uri, {
			method: 'PUT',
			body: jsonString,
			headers: {
				'Content-type': 'application/json; charset=UTF-8'
			}
		})
			.then((response) => {
				console.log(response)
				if (!response.ok) {
					throw new Error(`HTTP error, status = ${response.status}`);
				}
				console.log("2) updateEmp() got json response from server : " + response);
				return response.text();
			})
			.then((data) => {
				// document.write(`<h2> Saved emp : ${data} </h2>`);
				console.log('3) updateEmp() json converted to js object : ' + data);
				result = data;
				responseHandler(result);
			}).catch(function(error) {
				console.warn('Something went wrong.', error);
				//document.write(`<h2> ${error} </h2>`);
				result = error;
				responseHandler(result);
			});
	}
	console.log('4) response will render after fetch(). returining null or error if any')
	return result;
}

function getAllEmp(responseHandler) {
	let result = null;
	const uri = `${baseUri}/employeeService`;
	console.log('1) getAllEmp() fetch() start');
	fetch(uri)
		.then((response) => {
			if (!response.ok) {
				throw new Error(`HTTP error, status = ${response.status}`);
			}
			console.log("2) getAllEmp() got json response from server : " + response);
			return response.json();
		})
		.then((data) => {
			// document.write(`<h2> ${data.empno}, ${data.name}, ${data.salary} </h2>`);
			console.log('3) getAllEmp() json converted to js object : ' + data);
			result="<ul>"
			data.map(e=>{ 
				result+=`<li> ${e.empno}, ${e.name}, ${e.salary} </li>` 				
			}) ;
			result+="</ul>";
			console.log("result="+result);
			responseHandler(result);
		}).catch(function(error) {
			console.warn('Something went wrong.', error);
			//document.write(`<h2> No data found </h2>`);
			result = error;
			responseHandler(result);
		});
		console.log('4) response will render after fetch(). returining null or error if any')
	return result;
}