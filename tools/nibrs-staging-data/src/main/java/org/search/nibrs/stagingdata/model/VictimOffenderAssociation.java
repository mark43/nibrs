/*
 * Copyright 2016 SEARCH-The National Consortium for Justice Information and Statistics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.search.nibrs.stagingdata.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.search.nibrs.stagingdata.model.segment.OffenderSegment;
import org.search.nibrs.stagingdata.model.segment.VictimSegment;


@Entity
public class VictimOffenderAssociation implements Serializable{

	private static final long serialVersionUID = 6131252716830673798L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer victimOffenderAssociationId; 
	
    @ManyToOne
    @JoinColumn(name = "victimSegmentId")
	private VictimSegment victimSegment; 
    
    @ManyToOne
    @JoinColumn(name = "offenderSegmentId")
	private OffenderSegment offenderSegment;
    
    @ManyToOne
    @JoinColumn(name = "victimOffenderRelationshipTypeId")
	private VictimOffenderRelationshipType victimOffenderRelationshipType;
	
	public VictimOffenderAssociation() {
		super();
	}

	public String toString(){
		ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		String resultWithoutOffenseSegment = ReflectionToStringBuilder.toStringExclude(this, "victimSegment", "offenderSegment");
		int index = StringUtils.indexOf(resultWithoutOffenseSegment, ",");
		
		StringBuilder sb = new StringBuilder(resultWithoutOffenseSegment);
		sb.insert(index + 1, "victimSegmentId=" + getVictimSegment().getVictimSegmentId() + ",");
		sb.insert(index + 1, "offenderSegmentId=" + getOffenderSegment().getOffenderSegmentId() + ",");
		
        return sb.toString();
	}

	public Integer getVictimOffenderAssociationId() {
		return victimOffenderAssociationId;
	}

	public void setVictimOffenderAssociationId(Integer victimOffenderAssociationId) {
		this.victimOffenderAssociationId = victimOffenderAssociationId;
	}

	public VictimSegment getVictimSegment() {
		return victimSegment;
	}

	public void setVictimSegment(VictimSegment victimSegment) {
		this.victimSegment = victimSegment;
	}

	public OffenderSegment getOffenderSegment() {
		return offenderSegment;
	}

	public void setOffenderSegment(OffenderSegment offenderSegment) {
		this.offenderSegment = offenderSegment;
	}

	public VictimOffenderRelationshipType getVictimOffenderRelationshipType() {
		return victimOffenderRelationshipType;
	}

	public void setVictimOffenderRelationshipType(VictimOffenderRelationshipType victimOffenderRelationshipType) {
		this.victimOffenderRelationshipType = victimOffenderRelationshipType;
	}

}