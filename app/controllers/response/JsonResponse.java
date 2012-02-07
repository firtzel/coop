package controllers.response;

import controllers.response.JsonResponse.Status;

public abstract class JsonResponse {
	public enum Status {
		ok, error
	}

	public final Status status;

	public JsonResponse(Status status) {
		this.status = status;
	}
}
