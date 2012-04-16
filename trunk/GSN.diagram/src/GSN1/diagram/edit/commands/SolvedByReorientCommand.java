/*
 * 
 */
package GSN1.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import GSN1.DecomposableSpinalElement;
import GSN1.SolvedBy;
import GSN1.SpinalElement;
import GSN1.diagram.edit.policies.GSN1BaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class SolvedByReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public SolvedByReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof SolvedBy) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof DecomposableSpinalElement && newEnd instanceof DecomposableSpinalElement)) {
			return false;
		}
		SpinalElement target = getLink().getChildSpinalelement();
		if (!(getLink().eContainer() instanceof DecomposableSpinalElement)) {
			return false;
		}
		DecomposableSpinalElement container = (DecomposableSpinalElement) getLink()
				.eContainer();
		return GSN1BaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistSolvedBy_4002(container, getLink(), getNewSource(),
						target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof SpinalElement && newEnd instanceof SpinalElement)) {
			return false;
		}
		DecomposableSpinalElement source = getLink().getParentSpinalElement();
		if (!(getLink().eContainer() instanceof DecomposableSpinalElement)) {
			return false;
		}
		DecomposableSpinalElement container = (DecomposableSpinalElement) getLink()
				.eContainer();
		return GSN1BaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistSolvedBy_4002(container, getLink(), source,
						getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setParentSpinalElement(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setChildSpinalelement(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected SolvedBy getLink() {
		return (SolvedBy) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected DecomposableSpinalElement getOldSource() {
		return (DecomposableSpinalElement) oldEnd;
	}

	/**
	 * @generated
	 */
	protected DecomposableSpinalElement getNewSource() {
		return (DecomposableSpinalElement) newEnd;
	}

	/**
	 * @generated
	 */
	protected SpinalElement getOldTarget() {
		return (SpinalElement) oldEnd;
	}

	/**
	 * @generated
	 */
	protected SpinalElement getNewTarget() {
		return (SpinalElement) newEnd;
	}
}