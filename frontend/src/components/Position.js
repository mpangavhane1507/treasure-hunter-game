import React from 'react';
import './Position.css';

function Position({ position, isSelected, onClick }) {
  const { revealed, isTreasure, proximityValue } = position;

  const getClassName = () => {
    const classes = ['position'];
    
    if (revealed) {
      classes.push('revealed');
      if (isTreasure) {
        classes.push('treasure');
      } else {
        classes.push(`proximity-${proximityValue}`);
      }
    } else if (isSelected) {
      classes.push('selected');
    }
    
    return classes.join(' ');
  };

  const getContent = () => {
    if (!revealed) return '';
    if (isTreasure) return '💎';
    return proximityValue;
  };

  return (
    <div className={getClassName()} onClick={onClick}>
      {getContent()}
    </div>
  );
}

export default Position;
