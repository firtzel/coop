package controllers.response;

public class SuccessResponse extends JsonResponse {
	public final String message;

	public SuccessResponse(String message) {
		super(Status.ok);
		this.message = message;
	}
}
